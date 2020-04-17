using Contracts.Models;
using Contracts.ViewModels.HotelsListModels;
using Contracts.ViewModels.RoomView;
using SoapClient.HotelSoap;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace SoapClient.Windows
{
    /// <summary>
    /// Interaction logic for RoomsList.xaml
    /// </summary>
    public partial class RoomsList : Window
    {
        public Hotel Hotel { get; set; }
        public List<Room> ListOfRooms { get; set; }

        public RoomsList(Hotel hotel)
        {
            InitializeComponent();
            Hotel = hotel;
            this.Title = hotel.Name;
            ListOfRooms = PrepareRoomsList();
            WindowStartupLocation = System.Windows.WindowStartupLocation.CenterScreen;
            InitializeComponent();
            listOfHotels.ItemsSource = ListOfRooms;
        }

        private void ChooseRoom(object sender, MouseButtonEventArgs e)
        {
            int i = 0;
            while (listOfHotels.SelectedIndex != i)
            {
                i++;
            }
            var room = ListOfRooms[i];
            //Close();
            var roomDetails = GetRoom(room.RoomId);
            if (roomDetails == null)
            {
                return;
            }
            var window = new RoomView(roomDetails);
            window.Show();
        }

        private RoomDetails GetRoom(int roomId)
        {
            try
            {
                var client = new HotelsPortClient();
                var request = new findRoomByIdRequest();
                request.id = roomId;
                var response = client.findRoomById(request);

                var room = new RoomDetails(
                    response.room.id,
                    response.room.roomName,
                    response.room.roomDescription,
                    ImageConversion(response.room.roomImagePath),
                    response.room.roomPrice,
                    response.room.roomQuantityOfPeople,
                    response.room.roomBathroom,
                    response.room.roomDesk,
                    response.room.roomFridge,
                    response.room.roomSafe,
                    response.room.roomTv);

                return room;
            }
            catch (Exception e)
            {
                MessageBox.Show(e.Message, "Błąd pobrania szczegółów pokoju", MessageBoxButton.OK);
                return null;
            }
        }

        private byte[] imageConversion(string imageName)
        {

            FileStream fs = new FileStream(imageName, FileMode.Open, FileAccess.Read);
            byte[] imgByteArr = new byte[fs.Length];
            fs.Read(imgByteArr, 0, Convert.ToInt32(fs.Length));
            fs.Close();

            return imgByteArr;
        }

        private byte[] ImageConversion(string imageName)
        {

            FileStream fs = new FileStream(imageName, FileMode.Open, FileAccess.Read);
            byte[] imgByteArr = new byte[fs.Length];
            fs.Read(imgByteArr, 0, Convert.ToInt32(fs.Length));
            fs.Close();

            return imgByteArr;
        }

        private List<Room> PrepareRoomsList()
        {
            var client = new HotelsPortClient();
            var request = new findAllRoomsByHotelIdRequest();
            request.hotelId = Hotel.Id;
            var response = client.findAllRoomsByHotelId(request);

            var list = new List<Room>();
            foreach (var item in response)
            {
                var room = new Room(item.id, item.roomName, item.roomDescription, ImageConversion(item.roomImagePath), item.roomPrice);
                list.Add(room);
            }

            //{
            //    new Room(1,"Pokój pierwszy apartamentowiec", 
            //        "Bardzo dobry apartamentowiec, nawet nie wiesz jak bardzo dobry weź go zarezerwuj człowieku! adssad sda asdasd gfdg d gsd fgfgds sfdgdfg fd gdggfdsds fsdsdf sdfd sdgsgdfgg fdgdfd gfdgfdfgsdg dg wsdsdfg asdasdsa sd adasd as ssdf sdfdsdfsd fsdfsdfsdfsdfs svgsf sdf sfsdf sdf fsdf sdsf sd fsdf sdsd fsd fsd sdf dsfs sdfsdfsd fsdf sdsf sdfds fs sfddsfsdfdssd sfssdfsdfsdf sdfsdfsdf ssd fsdfdssfs dfsdfsd sfsfsf sd sfsdfsdf dssdf dsdsfsdfsdf sdf fsfsdff fsdf sfssd fsdfsf sdfsfdfsdfssf sfssfsdfsfs sdfsfsdfsdf sfsfsf sdfsdfsdsdfsd sdfsddsf sdfsdsdfsdf sf sdf sddfsd fsfsd  fdssd fsdf ssfsdfsfsdf sdfssdfsdf sdf sdf sdfsf sdf ssfdsdfsdfsdf sdsfs dssdasdasdsadadas dasd asdas dasdaasdasdasdas dasdfsdfsdfds sdf sdf dssdfsdfdsdssdfsd sdf s",
            //        imageConversion("room1.jpg"), 
            //        199.99),
            //    new Room(2,"Pokój rodzice + dziecko",
            //       "Bardzo fajny apartamentowiec, dzieciak będzie zadowolony, weź go zarezerwuj rodzicu!",
            //        imageConversion("room2.jpg"),
            //        399.99),
            //    new Room(3,"Pokój pierwszy apartamentowiec",
            //        "Bardzo dobry apartamentowiec, nawet nie wiesz jak bardzo dobry  sfsdf sdf fsdf sdsf sd fsdf sdsd fsd fsd sdf dsfsds fs",
            //        imageConversion("room3.jpg"),
            //        699.99),
            //    new Room(4,"Pokój rodzice + dziecko",
            //       "Bardzo fajny apartamentowiec, dzieciak będzie zadowolony, weź go zarezerwuj rodzicu!",
            //        imageConversion("room4.jpg"),
            //        6334.00),
            //    new Room(5,"Pokój pierwszy apartamentowiec",
            //        "Bardzo dobry apartamentowiec, nawet nie wiesz jakdfs svgs sdf fsdf sd fsd fsd sdf dsfsds fs",
            //        imageConversion("room5.jpg"),
            //        45.34),
            //    new Room(6,"Pokój rodzice + dziecko",
            //       "Bardzo fajny apartamentowiec, dzieciak będzie zadowolony, weź go zarezerwuj rodzicu!",
            //        imageConversion("room6.jpg"),
            //        234.69),
            //};

            return list;
        }

        private void RoomNameEnter(object sender, RoutedEventArgs e)
        {
            if (RoomNameBox.Text == "Wyszukaj pokoju")
            {
                RoomNameBox.Text = "";
                RoomNameBox.Foreground = new SolidColorBrush((Color)ColorConverter.ConvertFromString("Black"));
            }
        }

        private void RoomNameLeave(object sender, RoutedEventArgs e)
        {
            if (RoomNameBox.Text == "" || RoomNameBox.Text == null)
            {
                RoomNameBox.Text = "Wyszukaj pokoju";
                RoomNameBox.Foreground = new SolidColorBrush((Color)ColorConverter.ConvertFromString("Silver"));
            }
        }


        //private void NumberValidationTextBox(object sender, TextCompositionEventArgs e)
        //{
        //    Regex regex = new Regex("[^0-9]+");
        //    e.Handled = regex.IsMatch(e.Text);
        //}
    }
}

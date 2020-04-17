using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using Contracts.Models;
using Contracts.ViewModels.HotelsListModels;
using Contracts.ViewModels.RoomView;
using SoapClient.HotelSoap;
using SoapClient.Windows.Authorization;

namespace SoapClient.Windows
{
    /// <summary>
    /// Interaction logic for RoomView.xaml
    /// </summary>
    public partial class RoomView : Window
    {
        private RoomDetails Room { get; set; }
        private Account CurrentUser { get; set; }
        public RoomView(RoomDetails room)
        {
            WindowStartupLocation = System.Windows.WindowStartupLocation.CenterScreen;
            Room = room;
            CurrentUser = (Account) Application.Current.Resources["user"];
            InitializeComponent();
            MenuData.DataContext = CurrentUser;
            ButtonData.DataContext = CurrentUser;
            RoomDetails.DataContext = Room;
        }

        private void ShowYourReservations(object sender, MouseButtonEventArgs e)
        {

        }

        private void Logout(object sender, RoutedEventArgs e)
        {
            Application.Current.Resources["user"] = null;
            var window = new LogIn();
            window.Show();
            Close();
        }

        private void GoBack(object sender, MouseButtonEventArgs e)
        {
            var client = new HotelsPortClient();
            var request = new findAllRoomsByHotelIdRequest();
            request.hotelId = Room.HotelId;
            var response = client.findAllRoomsByHotelId(request);
        }
    }
}

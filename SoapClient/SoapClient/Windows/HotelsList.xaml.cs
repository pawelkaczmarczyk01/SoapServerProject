using Contracts.Models;
using Contracts.ViewModels.HotelsListModels;
using SoapClient.HotelSoap;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.ServiceModel;
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

namespace SoapClient.Windows
{
    /// <summary>
    /// Interaction logic for HotelsList.xaml
    /// </summary>
    public partial class HotelsList : Window
    {
        public List<Hotel> ListOfHotels { get; set; }

        public HotelsList(List<Hotel> list)
        {
            ListOfHotels = list;
            WindowStartupLocation = System.Windows.WindowStartupLocation.CenterScreen;
            InitializeComponent();
            listOfHotels.ItemsSource = ListOfHotels;
        }

        private void ChooseHotel(object sender, MouseButtonEventArgs e)
        {
            int i = 0;
            while (listOfHotels.SelectedIndex != i)
            {
                i++;
            }
            var hotel = ListOfHotels[i];
            //Close();
            var roomsList = new RoomsList(hotel);
            roomsList.Show();
        }

    }
}

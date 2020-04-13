using Contracts.ViewModels.HotelsListModels;
using System;
using System.Collections.Generic;
using System.IO;
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

namespace SoapClient.Windows
{
    /// <summary>
    /// Interaction logic for HotelsList.xaml
    /// </summary>
    public partial class HotelsList : Window
    {
        public List<Hotel> hotelsList { get; set; }
        public HotelsList()
        {
            hotelsList = PrepareHotelsList();
            WindowStartupLocation = System.Windows.WindowStartupLocation.CenterScreen;
            InitializeComponent();
            listOfHotels.ItemsSource = hotelsList;
        }

        private byte[] imageConversion(string imageName)
        {

            FileStream fs = new FileStream(imageName, FileMode.Open, FileAccess.Read);
            byte[] imgByteArr = new byte[fs.Length];
            fs.Read(imgByteArr, 0, Convert.ToInt32(fs.Length));
            fs.Close();

            return imgByteArr;
        }

        private List<Hotel> PrepareHotelsList()
        {

            return new List<Hotel>
            {
                new Hotel("Super fajny hotel 5 gwiazdek", imageConversion("hotel1.jpg")),
                new Hotel("Hotel Trivago", imageConversion("okrasa.jpg")),
                new Hotel("Hotel Gołębiewski no prawie", imageConversion("lena.jpg")),
                new Hotel("Fajny hotel", imageConversion("dubaj.jpg")),
            };
        }
    }
}

using Contracts.Models;
using Contracts.Responses;
using Contracts.ViewModels.HotelsListModels;
using SoapClient.HotelSoap;
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

namespace SoapClient.Windows.Authorization
{
    /// <summary>
    /// Interaction logic for LogIn.xaml
    /// </summary>
    public partial class LogIn : Window
    {
        public LogIn()
        {
            WindowStartupLocation = System.Windows.WindowStartupLocation.CenterScreen;
            InitializeComponent();
        }


        private void Login(object sender, EventArgs e)
        {
            if (LoginTextBox.Text.Equals("") ||
                LoginTextBox.Text.Equals("Login") ||
                LoginTextBox.Text == null ||
                PasswordTextBoxP.Password.Equals("") ||
                PasswordTextBoxP.Password.Equals("Hasło") ||
                PasswordTextBoxP.Password == null)
            {
                MessageBox.Show("Błędny login lub hasło.", "Błąd", MessageBoxButton.OK, MessageBoxImage.Error);
            }

            else
            {
                var user = GetUserByLoginAndPassword(LoginTextBox.Text, PasswordTextBoxP.Password);

                if (user != null)
                {
                    if (user.IsAdmin)
                    {
                        var window = new MainWindow();
                        Close();
                        window.Show();
                    }
                    else
                    {
                        try
                        {
                            Application.Current.Resources["user"] = user;
                            var list = PrepareHotelsList();
                            var window = new HotelsList(list);
                            Close();
                            window.Show();
                        }
                        catch (Exception)
                        {

                        }
                    }
                }
            }
        }

        private Account GetUserByLoginAndPassword(string login, string password)
        {
            var client = new HotelsPortClient();
            var request = new loginRequest();
            request.login = login;
            request.password = password;

            try
            {
                var response = client.login(request);
                var user = new Account(login, response.user.userName, response.user.userLastName, response.isAdmin);
                return user;
            }
            catch (Exception e)
            {
                MessageBox.Show(e.Message, "Błąd logowania", MessageBoxButton.OK, MessageBoxImage.Error);
                return null;
            }
        }

        private byte[] ImageConversion(string imageName)
        {

            FileStream fs = new FileStream(imageName, FileMode.Open, FileAccess.Read);
            byte[] imgByteArr = new byte[fs.Length];
            fs.Read(imgByteArr, 0, Convert.ToInt32(fs.Length));
            fs.Close();

            return imgByteArr;
        }

        private List<Hotel> PrepareHotelsList()
        {
            try
            {
                var client = new HotelsPortClient();
                var request = new findAllHotelsRequest();
                var response = client.findAllHotels(request);


                var list = new List<Hotel>();
                foreach (var item in response)
                {
                    var hotel = new Hotel(item.id, item.hotelName, ImageConversion(item.hotelImagePath));
                    list.Add(hotel);
                }

                return list;
            }
            catch (Exception e)
            {
                MessageBox.Show("Błąd", e.Data.ToString(), MessageBoxButton.OK);
                return new List<Hotel>();
            }
        }

        private void SignUp(object sender, EventArgs e)
        {
            Registration window = new Registration();
            Close();
            window.Show();
        }

        private void LoginEnter(object sender, RoutedEventArgs e)
        {
            if (LoginTextBox.Text == "Login")
            {
                LoginTextBox.Text = "";
                LoginTextBox.Foreground = new SolidColorBrush((Color)ColorConverter.ConvertFromString("Black"));
            }
        }

        private void LoginLeave(object sender, RoutedEventArgs e)
        {
            if (LoginTextBox.Text == "" || LoginTextBox.Text == null)
            {
                LoginTextBox.Text = "Login";
                LoginTextBox.Foreground = new SolidColorBrush((Color)ColorConverter.ConvertFromString("Silver"));
            }
        }

        private void PasswordEnter(object sender, RoutedEventArgs e)
        {
            if (PasswordTextBox.Text == "Hasło")
            {
                PasswordTextBox.Text = "";
                PasswordTextBox.Foreground = new SolidColorBrush((Color)ColorConverter.ConvertFromString("Black"));
            }
        }

        private void PasswordLeave(object sender, RoutedEventArgs e)
        {
            if (PasswordTextBoxP.Password == "" || PasswordTextBoxP.Password == null)
            {
                PasswordTextBox.Text = "Hasło";
                PasswordTextBox.Foreground = new SolidColorBrush((Color)ColorConverter.ConvertFromString("Black"));
            }
        }
    }
}

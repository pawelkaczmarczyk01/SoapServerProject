using Contracts.Models;
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
                var account = new Account(LoginTextBox.Text, PasswordTextBoxP.Password, "", "", "", false);
                if (account != null)
                {
                    if (account.IsAdmin)
                    {
                        var window = new MainWindow();
                        Close();
                        window.Show();
                    }
                    else
                    {
                        var window = new HotelsList(account);
                        Close();
                        window.Show();
                    }
                }
                else
                {
                    MessageBox.Show("Błędny login lub hasło.", "Błąd", MessageBoxButton.OK, MessageBoxImage.Error);
                }
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
                PasswordTextBox.Foreground = new SolidColorBrush((Color)ColorConverter.ConvertFromString("Silver"));
            }
        }
    }
}

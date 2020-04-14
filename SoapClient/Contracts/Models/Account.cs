using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Contracts.Models
{
    public class Account
    {
        public string Login { get; set; }
        public string Password { get; set; }
        public string Name { get; set; }
        public string LastName { get; set; }
        public string Email { get; set; }
        public bool IsAdmin { get; set; }

        public Account(string login, 
            string password, 
            string name,
            string lastName,
            string email,
            bool isAdmin)
        {
            Login = login;
            Password = password;
            Name = name;
            LastName = lastName;
            Email = email;
            IsAdmin = isAdmin;
        }
    }
}

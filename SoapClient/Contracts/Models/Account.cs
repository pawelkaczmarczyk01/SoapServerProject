﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Contracts.Models
{
    public class Account
    {
        public string Login { get; set; }
        public string Name { get; set; }
        public string LastName { get; set; }
        public bool IsAdmin { get; set; }

        public Account(string login, 
            string name,
            string lastName,
            bool isAdmin)
        {
            Login = login;
            Name = name;
            LastName = lastName;
            IsAdmin = isAdmin;
        }
    }
}

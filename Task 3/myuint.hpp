#include <iostream>
#include <vector>
#include <math.h>
using namespace std;

template <int Template>
class myuint
{
    private:
        vector<short> digits; 

    public:
        myuint(string value);
        myuint(int value);
        myuint();
    
    //----- Part One -----

        myuint<Template> operator-(const myuint<Template> &value) const; //method overloading to accept myuint strings and int
        myuint<Template> operator-(const string &value) const;
        myuint<Template> operator-(const int &value) const;

        myuint<Template> operator-=(const myuint<Template>  &value);
        myuint<Template> operator-=(const string &value);
        myuint<Template> operator-=(const int &value);

        myuint<Template> operator+(const myuint<Template> &value) const;
        myuint<Template> operator+(const string &value) const;
        myuint<Template> operator+(const int &value) const; 

        myuint<Template> operator+=(const myuint<Template>  &value);
        myuint<Template> operator+=(const string &value);
        myuint<Template> operator+=(const int &value); 

        myuint<Template> Subtract(const myuint<Template> &other) const;
        myuint<Template> Subtract(const string &other) const;
        myuint<Template> Add(const myuint<Template> &other) const;
        myuint<Template> Add(const string &other) const;

        myuint<Template> operator++(int);
        myuint<Template> operator++();
        myuint<Template> operator--(int);
        myuint<Template> operator--();
        
        myuint<Template> operator<<(const string &value);
        myuint<Template> operator<<(const int &value);
        myuint<Template> operator>>(const string &value);
        myuint<Template> operator>>(const int &value);

        bool operator!=(const myuint<Template> &value) const;
        bool operator==(const myuint<Template> &value) const;
        bool operator<(const myuint<Template> &value) const;
        bool operator>(const myuint<Template> &value) const;
        bool operator<=(const myuint<Template> &value) const;
        bool operator>=(const myuint<Template> &value) const;
        int whichisLarger(const myuint<Template> &value) const;
       
    //----- Part Two -----

        myuint<Template> operator*(const myuint<Template> &value) const;
        myuint<Template> operator*(const int &value) const;
        myuint<Template> Multiply(const myuint<Template> &other) const;

        myuint<Template> operator/(const int &value) const; 
        myuint<Template> Divide(int divisor) const;

        myuint<Template> operator%(const int &value) const; 
        myuint<Template> Modulus(int moduli) const;

        template<typename type>
        type convert_to();
        template <int Temp>
        friend ostream& operator<<(ostream& stream, const myuint<Temp> &obj);
};



template <int Temp>
ostream& operator<<(ostream& stream, const myuint<Temp> &obj)
{
    string str = "";
    bool leading_zero = true;   
    for (int i=0; i<obj.digits.size();i++)
    {
        if(obj.digits[i]!=0)
        {
            leading_zero=false;
        }

        if(leading_zero==false)
        {
            str.push_back(obj.digits[i] + '0');
        }
    }
    if(leading_zero==true) // ans is zero
    {
        stream << "0";
    }
     
    stream << str;    

    return stream; 
} 

template <int Template>
myuint<Template> myuint<Template>::operator++(int)
{   
    myuint<Template> ans = (*this);
    (*this) = (ans).Add("1");
    return ans;
}

template <int Template>
myuint<Template> myuint<Template>::operator++() 
{   
    (*this) = (*this).Add("1");
    myuint<Template> ans = (*this);
    return ans;
}

template <int Template>
myuint<Template> myuint<Template>::operator--(int)
{   
    myuint<Template> ans = (*this);
    (*this) = (ans).Subtract("1");
    return ans;
}

template <int Template>
myuint<Template> myuint<Template>::operator--() 
{   
    (*this) = (*this).Subtract("1");
    myuint<Template> ans = (*this);
    return ans;
}

template <int Template>
myuint<Template> myuint<Template>::operator+=(const myuint<Template>  &value)
{
    return (*this) = (*this).Add(value);
}

template <int Template>
myuint<Template> myuint<Template>::operator+=(const string &value)
{
    return (*this) = (*this).Add(value);
}

template <int Template>
myuint<Template> myuint<Template>::operator+=(const int &value)
{
    string val = to_string(value);
    return (*this) = (*this).Add(value);
}

template <int Template>
myuint<Template> myuint<Template>::operator-=(const myuint<Template>  &value)
{
    return (*this) = (*this).Subtract(value);
}

template <int Template>
myuint<Template> myuint<Template>::operator-=(const string &value)
{
    return (*this) = (*this).Subtract(value);
}

template <int Template>
myuint<Template> myuint<Template>::operator-=(const int &value)
{
    string val = to_string(value);
    return (*this) = (*this).Subtract(val);
}

template <int Template>
bool myuint<Template>::operator==(const myuint<Template> &value) const
{
    if((*this).digits==value.digits)
    {
        return true;
    }
    else
    {
        return false;
    }
}

template <int Template>
bool myuint<Template>::operator!=(const myuint<Template> &value) const
{
    if((*this).digits!=value.digits)
    {
        return true;
    }
    else
    {
        return false;
    }
}

template <int Template>
myuint<Template> myuint<Template>::operator*(const myuint<Template> &value) const
{   
    myuint<Template> ans = (*this).Multiply(value);
    return ans;
}

template <int Template>
myuint<Template> myuint<Template>::operator*(const int &value) const
{   
    string val = to_string(value);
    myuint<Template> ans = (*this).Multiply(val);
    return ans;
}

template<int Template>
myuint<Template> myuint<Template>::operator<<(const string &value)
{   int val = stoi(value);

    for(int i=0; i<val; i++)
    {
        (*this).digits.erase((*this).digits.begin(), (*this).digits.begin()+1);
        (*this).digits.push_back(0);
    }

    return (*this);
}

template<int Template>
myuint<Template> myuint<Template>::operator<<(const int &value)
{  

    for(int i=0; i<value; i++)
    {
        (*this).digits.erase((*this).digits.begin(), (*this).digits.begin()+1);
        (*this).digits.push_back(0);
    }

   

    return (*this);
}

template<int Template>
myuint<Template> myuint<Template>::operator>>(const string &value)
{  
    int val = stoi(value);
    for(int i=0; i<val; i++)
    {
        (*this).digits.insert((*this).digits.begin(), 0);
    }

    (*this).digits.erase((*this).digits.end()-val, (*this).digits.end());

    return (*this);
}

template<int Template>
myuint<Template> myuint<Template>::operator>>(const int &value)
{  
    for(int i=0; i<value; i++)
    {
        (*this).digits.insert((*this).digits.begin(), 0);
    }

    (*this).digits.erase((*this).digits.end()-value, (*this).digits.end());

    return (*this);
}

template <int Template>
myuint<Template> myuint<Template>::operator/(const int &value) const
{
    myuint<Template> ans = (*this).Divide(value);
    return ans;
}


template <int Template>
myuint<Template> myuint<Template>::operator%(const int &value) const
{
    myuint<Template> ans = (*this).Modulus(value);
    return ans;
}

template <int Template>
myuint<Template> myuint<Template>::operator+(const myuint<Template> &value) const
{   
    myuint<Template> ans = (*this).Add(value);
    return ans;
}

template <int Template>
myuint<Template> myuint<Template>::operator+(const string &value) const
{   
    myuint<Template> ans = (*this).Add(value);
    return ans;
}

template<int Template>
myuint<Template> myuint<Template>::operator+(const int &value) const
{   
    string val = to_string(value);
    myuint<Template> ans = (*this).Add(val);
    return ans;
}

template <int Template>
myuint<Template> myuint<Template>::operator-(const myuint<Template> &value) const
{
    if((*this) < value)
    {
        cout << "Error: unsigned value doesnt support negative outcomes." << endl;
        _Exit(0);
    }

    myuint<Template> ans = (*this).Subtract(value);
    return ans;
}

template <int Template>
myuint<Template> myuint<Template>::operator-(const int &value) const
{  
    string val = to_string(value);
    if((*this) < val)
    {
        cout << "Error: unsigned value doesnt support negative outcomes." << endl;
        _Exit(0);
    }
    myuint<Template> ans = (*this).Subtract(val);
    return ans;
}

template <int Template>
myuint<Template> myuint<Template>::operator-(const string &value) const
{  
     if((*this) < value)
    {
        cout << "Error: unsigned value doesnt support negative outcomes." << endl;
        _Exit(0);
    }
    myuint<Template> ans = (*this).Subtract(value);
    return ans;
}

template <int Template> 
bool myuint<Template>::operator>(const myuint<Template> &value) const
{
    int i = (*this).whichisLarger(value);
    if(i== 1 || i==4)
    {
        return true;
    }
    else
    {
        return false;
    }
}

template <int Template> 
bool myuint<Template>::operator<(const myuint<Template> &value) const
{
    int i = (*this).whichisLarger(value);
    if(i== 0 || i==3)
    {
        return true;
    }
    else
    {
        return false;
    }
}

template <int Template> 
bool myuint<Template>::operator>=(const myuint<Template> &value) const
{
    int i = (*this).whichisLarger(value);
    if(i== 1 || i==4 || i==5)
    {
        return true;
    }
    else
    {
        return false;
    }
}

template <int Template> 
bool myuint<Template>::operator<=(const myuint<Template> &value) const
{
    int i = (*this).whichisLarger(value);
    if(i== 0 || i==3 || i==5)
    {
        return true;
    }
    else
    {
        return false;
    }
}

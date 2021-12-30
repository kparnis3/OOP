#include <iostream>
#include <vector>
#include <math.h>
using namespace std;

//template<int Template>
//class myuint;
//template<int Template>
//myuint<Template> operator+(myuint<Template> value);

template <int Template>
class myuint
{
    private:
        vector<int> digits; 

    public:
        myuint(string value);
        myuint(); //if empty
    
    //----- Part One -----

    //what to implement
        myuint<Template> operator-(myuint<Template> value); //subtraction
        
        myuint<Template> operator+(const myuint<Template> &value) const;
        myuint<Template> operator+(const string &value) const;
        myuint<Template> operator+=(const myuint<Template>  &value);
        myuint<Template> operator+=(const string &value);
        myuint<Template> Add(const myuint<Template> &other) const;
        myuint<Template> Add(const string &other) const;

        myuint<Template> operator++(int);
        myuint<Template> operator++();
        

    //  myuint<Template> operator<<(myuint<Template> value); left shift
    //  myunit<Template> operator>>(myuint<Template> value); right shift
        bool operator!=(const myuint<Template> &value) const;
        bool operator==(const myuint<Template> &value) const;
        bool operator<(const myuint<Template> &value) const; //less then
        bool operator>(const myuint<Template> &value) const; //greater than
        bool operator<=(const myuint<Template> &value) const;
        bool operator>=(const myuint<Template> &value) const;
        int whichisLarger(const myuint<Template> &value) const;
       
    //----- Part Two -----

    //  myuint<Template> operator*(myuint<Template>) multiplication
    //  myuint<Template> operator/(myuint<Template>) division
    //  myuint<Template> operator%(myuint<Templates>) remainder

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

template <int Template>
myuint<Template> myuint<Template>::operator-(myuint<Template> value)
{
    //To be implemented
    myuint<Template> ans;
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

#include <iostream>
#include "myuint.hpp"
#include <algorithm>
#include <stdlib.h>
using namespace std;

template <int Template>
myuint<Template>::myuint(string value)
{
    static_assert(floor(log2(Template)) == log2(Template), "Must be 2^n bits");
    static_assert(Template > 0, "invalid bit amount");
    
    if(value.size() > Template)
    {
        cout<<"Error: size exceeds bitsize" << endl;
        _Exit(-1);
    } 

    for(int i =0; i< (Template-value.size()); i++)
    {
        digits.push_back(0);
    }
    for(int i = 0; i<value.size(); i++)
    {
        digits.push_back(value[i] - 48);
    }
}


template <int Template>
myuint<Template>::myuint()
{
    static_assert(floor(log2(Template)) == log2(Template), "Must be 2^n bits");
    static_assert(Template > 0, "invalid bit amount");
}

template <int Template>
myuint<Template> myuint<Template>::Add(const myuint<Template> &other) const
{
    myuint<Template> left = *this;

    int carry = 0;
    if(left.digits.size()!=other.digits.size())
    {
        cout << "Error: num's need to be of same size" << endl;
        _Exit(0);

    }

    string str = "";
    for(int i=left.digits.size()-1; i > 0; --i)
    {
        int sum = (left.digits[i] + other.digits[i] + carry);
        str.push_back(sum%10 + '0');


        carry=sum/10;
    }
    
    reverse(str.begin(), str.end());
    myuint<Template> ans(str);

    return ans;
}

template <int Template>
int myuint<Template>::whichisLarger(const myuint<Template> &value) const
{
    int count_left=0, count_right=0;

    bool leading_zero_left=true, leading_zero_right = true;

    for (int i=0; i<value.digits.size();i++)
    {
        if((*this).digits[i]!=0)
        {
            leading_zero_left=false;
        }

        if(value.digits[i]!=0)
        {
            leading_zero_right=false;
        }

        if(leading_zero_left==false)
        {
            count_left++;
        }

        if(leading_zero_right==false)
        {
            count_right++;
        }
    }
    
    if(count_left < count_right)
    {
        return 0; 
    }
    else if(count_left > count_right)
    {
        return 1;
    }

    for (int i = value.digits.size() - count_left; i<value.digits.size(); i++) //doesnt matter what we take since equal
    {
       if((*this).digits[i]<value.digits[i])
        {
            return 3;
        }
       else if((*this).digits[i]>value.digits[i])
        {
            return 4;
        }
    }

    return 5;
}

template <int Template>
myuint<Template> myuint<Template>::Add(const string &other) const
{
    myuint<Template> left = *this;
    myuint<Template> new_val(other);

    int carry = 0;
    if(left.digits.size()!=new_val.digits.size())
    {
        cout << "Error: num's need to be of same size" << endl;
        _Exit(0);

    }

    string str = "";
    for(int i=left.digits.size()-1; i > 0; --i)
    {
        int sum = (left.digits[i] + new_val.digits[i] + carry);
        str.push_back(sum%10 + '0');


        carry=sum/10;
    }
    
    reverse(str.begin(), str.end());
    myuint<Template> ans(str);

    return ans;
}


int main()
{ 
    myuint<1024> i("20");
    myuint<1024> j("3980");

    myuint<1024> ans1 = i + j;
    cout<<"Ans1: " << ans1 << endl;

    ans1 += i;

    cout<<"Ans1: " << ans1 << endl;

    ans1 += "100";

    cout<<"Ans1: " << ans1 << endl;

    cout << ans1 << endl;

    myuint<1024> ans2 = i + "100";
    
    cout << ans2 << endl;

    myuint<1024> k("1");
    myuint<1024> ans3 = k++;
    cout << "Ans3: " << ans3 << " k: " << k << endl;

    myuint<1024> L("1");
    myuint<1024> ans4 = ++L;
    cout << "Ans3: " << ans4 << " L: " << L << endl;

    cout << i << endl;

    if(k==L)
    {
        cout << "Both are equal" << endl;
    }
    else
    {
        cout << "Result isn't equal" << endl;
    }

    myuint<1024> m("1000");
    myuint<1024> y("1000");

    if(m<y)
    {
        cout << "y is greater" << endl;
    }
    else if (m>y)
    {
        cout << "m is greater" <<endl;
    }
    else
    {
        cout << "Both are equal" << endl;
    }

    myuint<1024> u("1000");
    myuint<1024> o("1000");

    if(u>=o)
    {
        cout << "u is greater or equal" << endl;
    }
    else
    {
        cout << "o is greater" << endl;
    }

    if(u!=o)
    {
         cout << "u is not equal o" << endl;
    }
    else
    {
        cout << "u is equal to o" << endl;
    }

    return 0;
}
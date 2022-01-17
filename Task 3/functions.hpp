#include <iostream>
#include <algorithm>
#include <stdlib.h>
using namespace std;

template <int Template>
myuint<Template>::myuint(string value)
{
    static_assert(floor(log2(Template)) == log2(Template), "Must be 2^n bits");
    static_assert(Template > 0, "invalid bit amount");
    int length = (Template-value.size());
    int length2 = value.size();
    if(value.size() > Template)
    {
        cout<<"Error: size exceeds bitsize" << endl;
        _Exit(-1);
    } 

    for(int i =0; i< length; i++)
    {
        digits.push_back(0);
    }
    for(int i = 0; i<length2; i++)
    {
        digits.push_back(value[i] - 48);
    }
}

template<int Template>
template<typename type>
type myuint<Template>::convert_to()
{
    int count=0;

    bool leading_zero=true;
    int length = (*this).digits.size();
    for (int i=0; i<length;i++)
    {
        if((*this).digits[i]!=0)
        {
            leading_zero=false;
        }

        if(leading_zero==false)
        {
            count++;
        }
    }

    reverse((*this).digits.begin(), (*this).digits.end());
    type decimal = 1;
    type value = 0;
  
    for(int i=0; i < count ; i++)
    {
        value+= (*this).digits[i] * decimal;
        decimal *=10;
    }

    return value;

}

template <int Template>
myuint<Template>::myuint(int value)
{
    string val = to_string(value);
    static_assert(floor(log2(Template)) == log2(Template), "Must be 2^n bits");
    static_assert(Template > 0, "invalid bit amount");
    int length = (Template-val.size());
    int length2 = val.size();
    if(val.size() > Template)
    {
        cout<<"Error: size exceeded" << endl;
        _Exit(-1);
    } 

    for(int i =0; i< length; i++)
    {
        digits.push_back(0);
    }
    for(int i = 0; i<length2; i++)
    {
        digits.push_back(val[i] - 48);
    }
}


template <int Template>
myuint<Template>::myuint()
{
    static_assert(floor(log2(Template)) == log2(Template), "Must be 2^n bits");
    static_assert(Template > 0, "invalid bit amount");
}

template <int Template>
myuint<Template> myuint<Template>::Subtract(const myuint<Template> &other) const
{
    myuint<Template> left = *this;
    string hold="";
    if(left.digits.size()!=other.digits.size())
    {
        cout << "Error: num's need to be of same size" << endl;
        _Exit(0);

    }
    int carry =0;
    int length = left.digits.size()-1;
    for(int i=length; i > 0; --i)
    {
        int sub = (left.digits[i] - other.digits[i] - carry);
        ;
        if(sub<0)
        {
            sub = sub + 10;
            carry = 1;
        }
        else
        {
            carry = 0;
        }

        hold.push_back(sub  + '0');
    }

    reverse(hold.begin(), hold.end());
    myuint<Template> ans(hold);

    return ans;
}

template <int Template>
myuint<Template> myuint<Template>::Divide(int divisor) const
{
    if(divisor==0)
    {
        cout<< "Error: attempting to divide by 0 "<< endl;
        exit(0);
    }
    int index = 0;
    int temp = (*this).digits[index];
    string hold ="";
    int length = (*this).digits.size();

    while(temp < divisor)
    {
        temp = temp * 10 + ((*this).digits[++index]);
    }

    while(length > index)
    {
        hold += (temp/divisor) + '0';

        temp = (temp % divisor) * 10 + ((*this).digits[++index]);
    }

    if((*this).digits.size() == 0)
    {
        myuint<Template> ans("0");
        return ans;
    }

    
    myuint<Template> ans(hold);
    return(ans);

} 

template <int Template>
myuint<Template> myuint<Template>::Modulus(int moduli) const
{
    int solution;
    int length = (*this).digits.size();
    for(int i =0; i<length; i++)
    {
        solution = (solution * 10 + (*this).digits[i]) % moduli;
    }
    
    
    
    myuint<Template> ans(to_string(solution));

    return ans;
}

template <int Template>
myuint<Template> myuint<Template>::Multiply(const myuint<Template> &other) const
{
    int count_left=0, count_right=0;

    bool leading_zero_left=true, leading_zero_right = true;
    int length = (*this).digits.size();
    for (int i=0; i<length;i++)
    {
        if((*this).digits[i]!=0)
        {   
            leading_zero_left=false;
        }
        
        if(other.digits[i]!=0)
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

    myuint<Template> left = *this; 

    vector<int> result(count_left + count_right, 0); 

    int num1=0;
    int num2=0;
    int length1 = (*this).digits.size();
    int length2 = (other).digits.size();
    for(int i=length1-1; i>=(length1 - count_left) ; i--)
    {  
        int carry = 0;

        num2=0;
        for(int j=length2-1; j>=(length2 - count_right) ; j--)
        {
            int sum = left.digits[i] * other.digits[i] + result[num1 + num2] + carry;
            carry =sum/10;

            result[num1 + num2] = sum % 10;
            
            num2++;
        }

        if(carry > 0)
        {
            result[num1 + num2] += carry;
        }
        num1++;
    }

    int i = result.size() -1;
    while (i>0 && result[i] == 0)
    {
        i--;
    }
    if(i==-1)
    {
        myuint<Template> ans1("0");

        return ans1; 
    }

    string hold = "";

    while (i>=0)
    {
        hold += to_string(result[i--]);
    }


    myuint<Template> ans(hold);

    return ans;


}

template <int Template>
myuint<Template> myuint<Template>::Add(const myuint<Template> &other) const
{
    myuint<Template> left = *this;

    int carry = 0;
    if(left.digits.size()!=other.digits.size())
    {
        cout << "Error: num's need to be of same size" << endl;
        _Exit(-1);

    }

    string hold = "";
    int length = left.digits.size();
    for(int i=length-1; i > 0; --i)
    {
        int sum = (left.digits[i] + other.digits[i] + carry);
        hold.push_back(sum%10 + '0');


        carry=sum/10;
    }
    
    reverse(hold.begin(), hold.end());
    myuint<Template> ans(hold);

    return ans;
}

template <int Template>
int myuint<Template>::whichisLarger(const myuint<Template> &value) const
{
    int count_left=0, count_right=0;

    bool leading_zero_left=true, leading_zero_right = true;
    int length = value.digits.size();
    for (int i=0; i<length;i++)
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

    for (int i = length - count_left; i<length; i++) //doesnt matter what we take since equal
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
myuint<Template> myuint<Template>::Subtract(const string &other) const
{
    myuint<Template> left = *this;
    myuint<Template> new_val(other);

    int carry = 0;
    string hold = "";
    for(int i=left.digits.size()-1; i > 0; --i)
    {
        int sub = (left.digits[i] - new_val.digits[i] - carry);
        ;
        if(sub<0)
        {
            sub = sub + 10;
            carry = 1;
        }
        else
        {
            carry = 0;
        }

        hold.push_back(sub  + '0');
    }

    reverse(hold.begin(), hold.end());
    myuint<Template> ans(hold);

    return ans;
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

    string hold = "";
    for(int i=left.digits.size()-1; i > 0; --i)
    {
        int sum = (left.digits[i] + new_val.digits[i] + carry);
        hold.push_back(sum%10 + '0');


        carry=sum/10;
    }
    
    reverse(hold.begin(), hold.end());
    myuint<Template> ans(hold);

    return ans;
}


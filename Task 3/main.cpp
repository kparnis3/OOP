#include <iostream>
#include "myuint.hpp"
#include <algorithm>
#include <stdlib.h>
using namespace std;

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

    myuint<1024> p = u-o;

    cout << p << endl;

    return 0;
}
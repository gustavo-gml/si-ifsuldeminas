using System;
class Ex1001
{
    static void Main()
    {
        double R, A;

        R = double.Parse(Console.ReadLine());

        A = 3.14159 * R * R;

        Console.WriteLine($"A={A:F4}");

    }
}
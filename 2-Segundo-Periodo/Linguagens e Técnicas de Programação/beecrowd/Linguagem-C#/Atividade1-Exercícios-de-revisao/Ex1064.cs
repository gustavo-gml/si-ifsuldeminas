using System;
class Ex1064
{
    static void Main()
    {
        float x, average = 0;
        int count = 0;

        for (int i = 0; i < 6; i++)
        {
            x = float.Parse(Console.ReadLine());
            if (x > 0)
            {
                count++;
                average += x;
            }
        }
        average /= count;

        Console.WriteLine($"{count} valores positivos");
        Console.WriteLine($"{average:F1}");
    }
}
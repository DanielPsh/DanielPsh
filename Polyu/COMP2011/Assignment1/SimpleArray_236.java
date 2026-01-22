//package comp2011.a1;

/**
 * Elementary tasks on arrays and running-time analysis.
 *
 * All submissions will be released on Blackboard, so please double check that you submission contains no identification information.
 *
 * Please do not modify the signatures of the methods.
 */

public class SimpleArray_236 { // Please replace 000 with your secret number!

    /**
     * VERY IMPORTANT.
     * 
     * I've sought help from the following GenAI:
     *     1. POE
     *     2. 
     *     3. 
     *     ... 
     * 
     * I've discussed this question with the following students (secret numbers, not names!):
     *     1. 
     *     2. 
     *     3. 
     *     ... 
     * 
     * I've sought help from the following Internet resources and books:
     *     1. https://www.w3schools.com/java/java_howto_find_smallest_array_el.asp
     *     2. 
     *     3. 
     *     ... 
     * 
     * Running time: O(   ).  
     */ 
    public static int q1(int[] a)
    {
        //
        int count = 0;
        int min = Integer.MAX_VALUE;

        //check if the array is not null
        if(a.length < 1)
        {
            return -1;
        }

        //
        for(int i = 0; i < a.length; i++)
        {
            if(a[i] < min)
            {
                min = a[i];
                count = 1;
            } else if(a[i] == min)
            {
                count++;
            }

            //
            if(count == 3)
            {
                return i;
            }
        }
        //
        return -1;

    }

    /**
     * VERY IMPORTANT.
     * 
     * I've sought help from the following GenAI:
     *     1. POE
     *     2. 
     *     3. 
     *     ... 
     * 
     * I've discussed this question with the following students (secret numbers, not names!):
     *     1. 
     *     2. 
     *     3. 
     *     ... 
     * 
     * I've sought help from the following Internet resources and books:
     *     1. 
     *     2. 
     *     3. 
     *     ... 
     * 
     * Running time: O(   ).  
     */ 
    public static int q2(int[] a)
    {
        //
        int count = 0;
        int min = Integer.MAX_VALUE;
        int temp = -1;

        //check if the array is not null
        if(a.length < 1)
        {
            return -1;
        }

        //
        for(int i = 0; i < a.length; i++)
        {
            if(a[i] < min)
            {
                min = a[i];
                count = 1;
                temp = -1;
            } else if(a[i] == min)
            {
                count++;
            }

            //
            if(count == 3)
            {
                temp = i;
            }
        }
        //
        if(temp != -1)
        {
            for(int i = temp + 1; i < a.length; i++)
            {
                if(a[i] == min)
                {
                    return -1;
                }
            }
            return temp;
        }
        //
        return -1;
    }

    /**
     * VERY IMPORTANT.
     * 
     * I've sought help from the following GenAI:
     *     1. POE
     *     2. 
     *     3. 
     *     ... 
     * 
     * I've discussed this question with the following students (secret numbers, not names!):
     *     1. 
     *     2. 
     *     3. 
     *     ... 
     * 
     * I've sought help from the following Internet resources and books:
     *     1. https://www.geeksforgeeks.org/dsa/find-minimum-element-in-a-sorted-and-rotated-array/
     *     2. 
     *     3. 
     *     ... 
     * 
     * Running time: O(   ).  
     */ 
    public static int q3(int[] a)
    {
        //
        int count = 0;
        int current = a[0];

        //
        if(a.length < 1)
        {
            return -1;
        }

        //
        for(int i = 0; i < a.length; i++)
        {
            if(a[i] == current)
            {
                count++;
            } else {
                count = 1;
                current = a[i];
            }

            //
            if(count == 3)
            {
                return i;
            }
        }
        //
        return -1;
    }

    /**
     * VERY IMPORTANT.
     * 
     * I've sought help from the following GenAI:
     *     1. POE
     *     2. 
     *     3. 
     *     ... 
     * 
     * I've discussed this question with the following students (secret numbers, not names!):
     *     1. 
     *     2. 
     *     3. 
     *     ... 
     * 
     * I've sought help from the following Internet resources and books:
     *     1. 
     *     2. 
     *     3. 
     *     ... 
     * 
     * Running time: O(   ).  
     */ 
    public static int q4(int[] a)
    {
        //
        int count = 0;
        int current = a[0];
        int temp = -1;

        //
        if(a.length <= 1)
        {
            return -1;
        }

        //
        for(int i = 0; i < a.length; i++)
        {
            if(a[i] == current)
            {
                count++;
                if(count == 2)
                {
                    temp = i;
                    return temp;
                }
            } else {
                count = 1;
                current = a[i];
            }

        }
        //
        return -1;
    }

    /**
     * VERY IMPORTANT.
     * 
     * I've sought help from the following GenAI:
     *     1. POE
     *     2. 
     *     3. 
     *     ... 
     * 
     * I've discussed this question with the following students (secret numbers, not names!):
     *     1. 
     *     2. 
     *     3. 
     *     ... 
     * 
     * I've sought help from the following Internet resources and books:
     *     1. https://www.geeksforgeeks.org/java/binary-search-in-java/
     *     2. 
     *     3. 
     *     ... 
     * 
     * Running time: O(   ).  
     */ 
    public static int q5(int[] a)
    {
        //
        int low = 0;
        int high = a.length - 1;

        //
        if(a.length < 1)
        {
            return -1;
        }

        //
        while(low <= high)
        {
            int mid = (low + high) / 2;

            if(a[mid] == mid)
            {
                return mid;
            } else if (a[mid] < mid)
            {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * VERY IMPORTANT.
     * 
     * I've sought help from the following GenAI:
     *     1. POE
     *     2. 
     *     3. 
     *     ... 
     * 
     * I've discussed this question with the following students (secret numbers, not names!):
     *     1. 
     *     2. 
     *     3. 
     *     ... 
     * 
     * I've sought help from the following Internet resources and books:
     *     1. 
     *     2. 
     *     3. 
     *     ... 
     * 
     * Running time: O(   ).  
     */ 
    public static int[] q6(int[] a1, int[] a2)
    {
        int count = 0;
        int maxCommon = Math.min(a1.length, a2.length);
        int[] common = new int[maxCommon];

        for(int i = 0; i < a1.length; i++)
        {
            for(int j = 0 ; j < a2.length; j++)
            {
                if (a1[i] == a2[j]) {
                    common[count] = a1[i];
                    count++;
                    break;
                }
            }
        }

        //
        int[] elements = new int[count];
        for(int a = 0; a < count; a++)
        {
            elements[a] = common[a];
        }

        return elements;
    }

    /**
     * VERY IMPORTANT.
     * 
     * I've sought help from the following GenAI:
     *     1. 
     *     2. 
     *     3. 
     *     ... 
     * 
     * I've discussed this question with the following students (secret numbers, not names!):
     *     1. 
     *     2. 
     *     3. 
     *     ... 
     * 
     * I've sought help from the following Internet resources and books:
     *     1. 
     *     2. 
     *     3. 
     *     ... 
     * 
     * Running time: O(   ).  
     */ 
    public static int q7(int[] a) {
        return -1;
    }

    /**
     * VERY IMPORTANT.
     * 
     * I've sought help from the following GenAI:
     *     1. 
     *     2. 
     *     3. 
     *     ... 
     * 
     * I've discussed this question with the following students (secret numbers, not names!):
     *     1. 
     *     2. 
     *     3. 
     *     ... 
     * 
     * I've sought help from the following Internet resources and books:
     *     1. 
     *     2. 
     *     3. 
     *     ... 
     * 
     * Running time: O(   ).  
     */ 
    public static int q8(int[] a) {
        return -1; // the correct answer must be nonnegative.
    }

    /**
     * VERY IMPORTANT.
     * 
     * I've sought help from the following GenAI:
     *     1. 
     *     2. 
     *     3. 
     *     ... 
     * 
     * I've discussed this question with the following students (secret numbers, not names!):
     *     1. 
     *     2. 
     *     3. 
     *     ... 
     * 
     * I've sought help from the following Internet resources and books:
     *     1. 
     *     2. 
     *     3. 
     *     ... 
     * 
     * Running time: O(   ).  
     */ 
    public static int q9(int[] a) {
        return -1; // the correct answer must be nonnegative.
    }

    /*
     * You can use any Java library here for testing.
     * Please perform extensive testing.
     */
    public static void main(String[] args) {
        int[] a = {1,2,3,4};
        int[] b = {5,5,6,7,8};
        int[] c = {-10, -5, 0, 3, 4, 7, 9};

        q1(a);
        int x = q1(a);
        System.out.println(x);

        q2(a);
        int y = q2(a);
        System.out.println(y);

        q3(a);
        int z = q3(a);
        System.out.println(z);

        q4(b);
        int num = q4(b);
        System.out.println(num);

        q5(c);
        int num1 = q5(c);
        System.out.println(num1);

        int[] arrayA = {1, 2, 3, 4, 5};
        int[] arrayB = {2, 4, 6, 8};

        int[] common = q6(arrayA, arrayB);


        System.out.print("Common elements: ");
        for (int num3 : common) {
            System.out.print(num3 + " ");
        }
    }
}

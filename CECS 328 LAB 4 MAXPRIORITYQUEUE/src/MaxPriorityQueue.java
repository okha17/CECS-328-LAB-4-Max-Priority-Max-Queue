// ===================================================================================
// CECS 328 Lab Assignment 4 Extra Credit
// ===================================================================================
// Team Members:
// Kevin Vu
// Osman Khan
// Anthony Dawson
// ===================================================================================
import java.util.Scanner;

public class MaxPriorityQueue {
	static int maximum;
	static void menu()
	{
		System.out.println("\nChoose from the following options: \n");
		System.out.println("1. Insert\n" +
						   "2. Maximum\n" +
						   "3. Extract-Max\n" +
						   "4. Increase-Key\n" +
						   "5. Exit\n");				   
	}
	
	static void setMaximum(int max)
	{
		maximum = max;
	}
	
	static int getMaximum()
	{
		return maximum;
	}
	
	static int parent(int arr[], int i)
	{
		if(i % 2 == 0)
		{
			i = i -1;
		}
		
	
		return i/2;
		
	}
	
    static void MaxHeapify(int arr[], int n, int i) 
    { 
        int largest = i; 
        int l = 2 * i + 1;
        int r = 2 * i + 2; 
  
        if (l < n && arr[l] > arr[largest]) 
            largest = l; 
  
        if (r < n && arr[r] > arr[largest]) 
            largest = r; 
  
        if (largest != i) 
        { 
            int temp = arr[i]; 
            arr[i] = arr[largest]; 
            arr[largest] = temp;
            MaxHeapify(arr, n, largest); 
        } 
    } 
   
    static void BuildMaxHeap(int arr[]) 
    { 
        int startIdx = (arr.length / 2) - 1; 
  
        for (int i = startIdx; i >= 0; i--) { 
            MaxHeapify(arr, arr.length, i); 
        } 
    } 
	
	static void print(int arr[])
	{
		for(int index = 0; index < arr.length; ++index)
		{
			System.out.print(arr[index] + " ");
		}
		System.out.println();	
	}
	
	static int[] insert(int arr[], int x)
	{
		
		int temp[] = new int[arr.length + 1];
		for(int index = 0; index < arr.length; ++index)
		{
			temp[index] = arr[index];
		}
		temp[arr.length] = x;
		BuildMaxHeap(temp);
		return temp;
	}
	
	static int[] ExtractMax(int arr[])
	{
		int temp[] = new int[arr.length - 1];
		if(arr.length < 0)
		{
			System.out.println("Heap underflow");
		}
		maximum = arr[0];
		arr[0] = arr[arr.length - 1];
		
		for(int index = 0; index < arr.length - 1; ++index)
		{
			temp[index] = arr[index];
		}
		MaxHeapify(temp, temp.length, 0);
		System.out.print("\nOutputted Max Heap: ");
		print(temp);
		System.out.println();
		return temp;
	}
	
	static int[] IncreaseKey(int arr[], int i, int key )
	{

		if(key < arr[i])
		{
			System.out.println("\nERROR: New key is smaller than current key!");
			return arr;
		}
		arr[i] = key;
		while(arr[parent(arr,i)] < arr[i])
		{
			int temp = arr[i];
			arr[i] = arr[parent(arr, i)];
			arr[parent(arr, i)] = temp;
			i = parent(arr, i);
			
		}
		System.out.print("\nOutputted Max Heap: ");
		print(arr);
		return arr;
		
		
	}
	
	@SuppressWarnings("resource")
	public static void main(String []args)
	{
		String a;
		Scanner input = new Scanner(System.in);
		int newArray[] = null;
		boolean isTrue = true;
		
		System.out.print("Input an array of integers seperated by spaces: ");
		a = input.nextLine();
		String arrayOfIntegers[] = a.split(" ");
		
		int intArray[] = new int[arrayOfIntegers.length];
		
		for(int index = 0; index < arrayOfIntegers.length; ++index)
		{
			intArray[index] = Integer.parseInt(arrayOfIntegers[index]);
		}
		System.out.print("\nOutputted Max Heap: " );
		BuildMaxHeap(intArray);
		print(intArray);
		
		while(isTrue)
		{
			int choice;
			
			menu();
			choice = input.nextInt();
			
			switch(choice)
			{
			case 1:
				System.out.print("\nInput the integer you want inserted: ");
				choice = input.nextInt();
				if(newArray == null)
				{
					newArray = insert(intArray,choice);
					System.out.print("\nOutputted Max Heap: " );
					print(newArray);
				}
				else
				{
					newArray = insert(newArray,choice);
					System.out.print("\nOutputted Max Heap: " );
					print(newArray);		
				}
				break;
			case 2:
				if(newArray == null)
				{
					System.out.println("\nMaximum value is: " + intArray[0]);
				}
				else 
				{
					System.out.println("\nMaximum value is: " + newArray[0]);
				}
				break;
			case 3:
				if(newArray == null)
				{
					newArray = ExtractMax(intArray);
					System.out.println("Maximum value is: " + getMaximum());
				}
				else 
				{
					newArray = ExtractMax(newArray);
					System.out.println("Maximum value is: " + getMaximum());
				}
				break;
			case 4:
				int node;
				int newValue;
				
				System.out.print("\nInput the index of the node you want to increase: ");
				node = input.nextInt();
				
				System.out.print("\nInput the new value: ");
				newValue = input.nextInt();
				
				if(newArray == null)
				{
					newArray = IncreaseKey(intArray, node - 1, newValue);
				}
				else 
				{
					newArray = IncreaseKey(newArray, node - 1, newValue);
				}
				break;
			case 5:
				isTrue = false;
				break;
			default:
				System.out.println("Invalid option, try again");
			}
		}
	
		
		
	
	}
}

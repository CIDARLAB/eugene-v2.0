num min_of(num[] arr)
{
    num size_arr = SIZE(arr);
    
    num min = arr[0];
    for(num i=1; i<size_arr; i=i+1) {
        if(min > arr[i]) {
            min = arr[i];
        }
    }
    
    return min;
}

num max_of(num[] arr)
{
    num size_arr = SIZE(arr);
    
    num max = arr[0];
    for(num i=1; i<size_arr; i=i+1) {
        if(max < arr[i]) {
            max = arr[i];
        }
    }
    
    return max;
}

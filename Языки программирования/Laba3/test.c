void array_fib(int* array, int* limit) {
    if(limit - array < 1) return;
    *array = 1;
    if(limit - array < 2) return;
    *(array + 1) = 1;
    if(limit - array <= 3) return;
    for(int i = 2; i < (limit - array); i++){
        *(array + i) = *(array + i - 1) + *(array + i - 2);
    }
}

int main(){
    int array[5];
    array_fib(&array, &array[6]); 
    printf("%d", array[0]);
}
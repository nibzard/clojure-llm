(defn f
  " Implement the function f that takes n as a parameter,
  and returns a list of size n, such that the value of the element at index i is the factorial of i if i is even
  or the sum of numbers from 1 to i otherwise.
  i starts from 1.
  the factorial of i is the multiplication of the numbers from 1 to i (1 * 2 * ... * i).
  Example:
  >>> (f 5)
  [1 2 6 24 15]"
  [n]
  (let [n (max 0 (or n 0))]
    (loop [i 1
           fact 1
           acc []]
      (if (> i n)
        acc
        (let [fact' (* fact i)
              v (if (even? i)
                  fact'
                  (quot (* i (inc i)) 2))]
          (recur (inc i) fact' (conj acc v)))))))
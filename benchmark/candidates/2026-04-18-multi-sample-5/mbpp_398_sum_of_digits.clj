(defn sum_of_digits
  "	Write a function to compute the sum of digits of each number of a given list."
  [nums]
  (letfn [(digit-sum [n]
            (reduce + 0
                    (map (comp #(Character/digit ^char % 10) identity)
                         (str (Math/abs (long n))))))]
    (mapv digit-sum (or nums []))))
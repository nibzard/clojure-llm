(defn armstrong_number
  "Write a function to check whether the given number is armstrong or not."
  [number]
  (let [digits (map #(Character/digit ^char % 10) (str (Math/abs (long number))))
        n (count digits)
        sum (reduce + (map #(long (Math/pow % n)) digits))]
    (= sum (Math/abs (long number)))))
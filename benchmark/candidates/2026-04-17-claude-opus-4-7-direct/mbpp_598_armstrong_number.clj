(defn armstrong_number
  "Write a function to check whether the given number is armstrong or not."
  [number]
  (let [digits (map #(Character/digit % 10) (str number))
        num-digits (count digits)]
    (= number (reduce + (map #(int (Math/pow % num-digits)) digits)))))
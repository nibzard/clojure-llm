(defn armstrong_number
  "	Write a function to check whether the given number is armstrong or not."
  [number]
  (let [digits (map #(Character/digit ^char % 10) (str number))
        power  (count digits)
        sum    (reduce + (map #(long (Math/pow % power)) digits))]
    (= number sum)))
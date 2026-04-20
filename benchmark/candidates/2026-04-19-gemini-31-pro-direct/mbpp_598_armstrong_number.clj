(defn armstrong_number
  "Write a function to check whether the given number is armstrong or not."
  [number]
  (let [s (str number)
        n (count s)
        digits (map #(Character/digit % 10) s)
        sum (reduce + (map #(reduce * (repeat n %)) digits))]
    (== number sum)))
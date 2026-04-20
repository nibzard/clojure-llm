(defn armstrong_number
  "	Write a function to check whether the given number is armstrong or not."
  [number]
  (let [s (str number)
        digits (map #(- (int %) (int \0)) s)
        power (count digits)]
    (= number
       (reduce + (map #(long (Math/pow % power)) digits)))))
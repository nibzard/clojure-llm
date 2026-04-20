(defn digit_distance_nums
  "	Write a cljthon function to find the sum of the per-digit difference between two integers."
  [n1 n2]
  (let [digits (fn [n]
                 (map #(Character/digit ^char % 10)
                      (str (Math/abs (long n)))))
        digits1 (digits n1)
        digits2 (digits n2)
        max-len (max (count digits1) (count digits2))
        digits1-pad (concat digits1 (repeat 0))
        digits2-pad (concat digits2 (repeat 0))]
    (reduce + (map #(Math/abs ^long (- %1 %2))
                   (take max-len digits1-pad)
                   (take max-len digits2-pad)))))
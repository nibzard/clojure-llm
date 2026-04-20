(defn digit_distance_nums
  "	Write a cljthon function to find the sum of the per-digit difference between two integers."
  [n1 n2]
  (let [digits (fn [n]
                 (map #(Character/digit ^char % 10)
                      (str (Math/abs (long n)))))
        d1 (reverse (digits n1))
        d2 (reverse (digits n2))
        max-len (max (count d1) (count d2))
        p1 (take max-len (concat d1 (repeat 0)))
        p2 (take max-len (concat d2 (repeat 0)))]
    (reduce + (map #(Math/abs (long (- %1 %2))) p1 p2))))
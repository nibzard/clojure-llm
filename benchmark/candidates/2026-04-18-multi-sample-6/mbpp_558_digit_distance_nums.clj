(defn digit_distance_nums
  "	Write a cljthon function to find the sum of the per-digit difference between two integers."
  [n1 n2]
  (let [digits (fn [n]
                 (map #(Character/digit ^char % 10)
                      (str (Math/abs (long n)))))]
    (->> (map (fn [d1 d2] (Math/abs ^long (- d1 d2)))
              (digits n1)
              (digits n2))
         (reduce + 0))))
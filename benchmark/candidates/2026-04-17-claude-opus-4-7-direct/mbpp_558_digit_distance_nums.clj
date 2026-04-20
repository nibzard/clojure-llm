(defn digit_distance_nums
  "Write a cljthon function to find the sum of the per-digit difference between two integers."
  [n1 n2]
  (let [digits (fn [n] (map #(Character/digit % 10) (str n)))]
    (reduce + (map #(Math/abs (- %1 %2)) (digits n1) (digits n2)))))
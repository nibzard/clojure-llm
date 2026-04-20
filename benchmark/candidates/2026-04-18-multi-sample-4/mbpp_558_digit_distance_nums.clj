(defn digit_distance_nums
  "	Write a cljthon function to find the sum of the per-digit difference between two integers."
  [n1 n2]
  (let [s1 (str (Math/abs (long n1)))
        s2 (str (Math/abs (long n2)))
        max-len (max (count s1) (count s2))
        p1 (concat (repeat (- max-len (count s1)) \0) s1)
        p2 (concat (repeat (- max-len (count s2)) \0) s2)]
    (reduce + (map #(Math/abs (- (Character/digit ^char %1 10)
                                 (Character/digit ^char %2 10)))
                   p1
                   p2))))
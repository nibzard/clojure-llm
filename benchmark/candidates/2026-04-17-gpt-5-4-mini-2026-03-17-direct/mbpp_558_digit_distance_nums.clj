(defn digit_distance_nums
  "Write a cljthon function to find the sum of the per-digit difference between two integers."
  [n1 n2]
  (let [s1 (str (Math/abs (long n1)))
        s2 (str (Math/abs (long n2)))
        len (max (count s1) (count s2))
        pad-left (fn [s]
                   (let [pad (apply str (repeat (- len (count s)) "0"))]
                     (str pad s)))]
    (reduce + (map (fn [a b]
                     (Math/abs ^long (- (Character/digit ^char a 10)
                                        (Character/digit ^char b 10))))
                   (pad-left s1)
                   (pad-left s2)))))
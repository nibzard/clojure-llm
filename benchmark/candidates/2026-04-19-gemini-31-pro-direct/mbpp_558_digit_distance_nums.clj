(defn digit_distance_nums [n1 n2]
  (let [s1 (str n1)
        s2 (str n2)
        len (max (count s1) (count s2))
        pad (fn [s] (str (apply str (repeat (- len (count s)) \0)) s))]
    (reduce + (map #(Math/abs (- (int %1) (int %2))) 
                   (pad s1) 
                   (pad s2)))))
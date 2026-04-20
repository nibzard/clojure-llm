(defn find_Element
  "	Write a cljthon function to find element at a given index after number of rotations."
  [arr ranges rotations index]
  (let [v (vec arr)
        n (count v)]
    (if (or (zero? n) (nil? index) (neg? index) (>= index n))
      nil
      (let [final-index
            (reduce
             (fn [idx [l r]]
               (if (and (<= l idx) (<= idx r))
                 (if (= idx l) r (dec idx))
                 idx))
             index
             (reverse (take rotations ranges)))]
        (get v final-index)))))
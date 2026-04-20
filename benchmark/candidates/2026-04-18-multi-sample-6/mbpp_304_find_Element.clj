(defn find_Element
  "	Write a cljthon function to find element at a given index after number of rotations."
  [arr ranges rotations index]
  (let [v (vec (or arr []))
        n (count v)]
    (if (or (zero? n) (nil? index) (neg? index) (>= index n))
      nil
      (let [final-index
            (reduce
             (fn [idx rot]
               (let [[l r] rot]
                 (if (and (some? l) (some? r) (<= l idx) (<= idx r))
                   (if (= idx l) r (dec idx))
                   idx)))
             index
             (take (min (max (or rotations 0) 0) (count ranges)) ranges))]
        (nth v final-index)))))
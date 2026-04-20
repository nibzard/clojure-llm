(defn find_Element
  "	Write a cljthon function to find element at a given index after number of rotations."
  [arr ranges rotations index]
  (let [v (vec (or arr []))
        n (count v)]
    (if (or (zero? n) (nil? index) (neg? index) (>= index n))
      nil
      (let [ops (take (or rotations 0) (or ranges []))
            original-index
            (reduce
             (fn [idx r]
               (let [[l h] r]
                 (if (and (some? l) (some? h) (<= l idx h))
                   (if (= idx l) h (dec idx))
                   idx)))
             index
             (reverse ops))]
        (nth v original-index nil)))))
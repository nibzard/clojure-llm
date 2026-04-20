(defn find_Element
  "	Write a cljthon function to find element at a given index after number of rotations."
  [arr ranges rotations index]
  (let [n (count arr)]
    (if (or (nil? arr)
            (zero? n)
            (nil? index)
            (neg? index)
            (>= index n))
      nil
      (let [final-index
            (reduce
             (fn [idx rot]
               (let [[l r] rot]
                 (if (and (<= l idx) (<= idx r))
                   (if (= idx l) r (dec idx))
                   idx)))
             index
             (reverse rotations))]
        (nth arr final-index))))
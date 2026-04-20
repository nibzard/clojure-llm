(defn find_Element
  "	Write a cljthon function to find element at a given index after number of rotations."
  [arr ranges rotations index]
  (let [v (vec (or arr []))
        n (count v)]
    (if (or (zero? n)
            (nil? index)
            (neg? index)
            (>= index n))
      nil
      (let [rs (take (max 0 (or rotations 0)) (or ranges []))
            original-index
            (reduce
             (fn [idx rng]
               (let [[l r] rng]
                 (if (and (<= l idx) (<= idx r))
                   (if (= idx l) r (dec idx))
                   idx)))
             index
             (reverse rs))]
        (nth v original-index)))))
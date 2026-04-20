(defn find_Element
  "	Write a cljthon function to find element at a given index after number of rotations."
  [arr ranges rotations index]
  (let [n (count arr)]
    (if (or (nil? arr)
            (empty? arr)
            (nil? index)
            (neg? index)
            (>= index n))
      nil
      (let [final-index
            (reduce
             (fn [idx rot]
               (let [[l r] (nth ranges rot nil)]
                 (if (and l r (<= l idx r))
                   (if (= idx l) r (dec idx))
                   idx)))
             index
             (range (dec (count rotations)) -1 -1))]
        (nth arr final-index)))))
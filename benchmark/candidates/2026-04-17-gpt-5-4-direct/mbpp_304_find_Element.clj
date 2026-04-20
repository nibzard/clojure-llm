(defn find_Element
  "Write a cljthon function to find element at a given index after number of rotations."
  [arr ranges rotations index]
  (let [n (count arr)]
    (if (or (nil? arr) (empty? arr) (nil? index) (neg? index) (>= index n))
      nil
      (let [final-index
            (reduce
             (fn [idx rot]
               (let [[l r] (nth ranges rot)]
                 (cond
                   (or (nil? l) (nil? r) (< idx l) (> idx r)) idx
                   (= idx l) r
                   :else (dec idx))))
             index
             (range (dec rotations) -1 -1))]
        (nth arr final-index)))))
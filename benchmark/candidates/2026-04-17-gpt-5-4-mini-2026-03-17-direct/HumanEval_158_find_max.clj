(defn find_max
  [words]
  (when (seq words)
    (let [unique-count (fn [s] (count (set s)))
          best (fn [a b]
                 (let [ca (unique-count a)
                       cb (unique-count b)]
                   (cond
                     (> ca cb) a
                     (< ca cb) b
                     :else (if (neg? (compare a b)) a b))))]
      (reduce best words))))
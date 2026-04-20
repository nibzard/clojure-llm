(defn problem-find-distinct-items [a]
  (reduce (fn [acc x]
            (if (some #(= x %) acc)
              acc
              (conj acc x)))
          []
          a))
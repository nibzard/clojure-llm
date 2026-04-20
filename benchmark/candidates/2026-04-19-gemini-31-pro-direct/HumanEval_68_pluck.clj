(defn pluck [arr]
  (let [evens (keep-indexed (fn [idx val] (when (even? val) [val idx])) arr)]
    (if (empty? evens)
      []
      (reduce (fn [a b]
                (if (< (first b) (first a)) b a))
              evens))))
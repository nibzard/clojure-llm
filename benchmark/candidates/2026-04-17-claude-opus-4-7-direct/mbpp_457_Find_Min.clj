(defn Find-Min
  "Write a cljthon function to find the sublist having minimum length."
  [lst]
  (when (seq lst)
    (reduce (fn [min-item current-item]
              (if (< (count current-item) (count min-item))
                current-item
                min-item))
            lst)))
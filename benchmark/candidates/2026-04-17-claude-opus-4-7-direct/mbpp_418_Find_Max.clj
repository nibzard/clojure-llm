(defn Find-Max
  "Write a cljthon function to find the element of a list having maximum length."
  [lst]
  (when (seq lst)
    (let [len-fn (fn [x] (if (string? x) (count x) 0))]
      (last (sort-by len-fn lst)))))
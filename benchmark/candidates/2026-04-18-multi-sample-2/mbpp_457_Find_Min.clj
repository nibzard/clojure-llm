(defn Find_Min
  "	Write a cljthon function to find the sublist having minimum length."
  [lst]
  (when (seq lst)
    (reduce (fn [min-sub sub]
              (if (< (count sub) (count min-sub))
                sub
                min-sub))
            lst)))
(defn solve [s]
  (when s
    (if (some #(Character/isLetter %) s)
      (apply str (map (fn [c]
                        (cond
                          (Character/isLowerCase c) (Character/toUpperCase c)
                          (Character/isUpperCase c) (Character/toLowerCase c)
                          :else c))
                      s))
      (clojure.string/reverse s))))
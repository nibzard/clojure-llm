(defn remove_uppercase
  "	Write a function to remove uppercase substrings from a given string."
  [str1]
  (if (nil? str1)
    nil
    (->> (re-seq #"[A-Z]+" str1)
         (reduce (fn [s upper]
                   (clojure.string/replace s upper ""))
                 str1))))
(defn file_name_check
  [file_name]
  (let [digits (count (filter #(Character/isDigit %) file_name))
        dots (count (filter #(= % \.) file_name))
        parts (clojure.string/split file_name #"\." -1)
        before-dot (first parts)
        after-dot (second parts)]
    (if (and 
         (<= digits 3)
         (= dots 1)
         (not (empty? before-dot))
         (Character/isLetter (first before-dot))
         (#{"txt" "exe" "dll"} after-dot))
      "Yes"
      "No")))
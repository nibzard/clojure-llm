(defn text_match_wordz_middle
  "Write a function that checks if a strings contains 'z', except at the start and end of the word."
  [text]
  (boolean
   (some #(and (>= (count %) 3)
               (some (fn [ch] (= ch \z))
                     (butlast (rest %))))
         (re-seq #"\S+" (or text "")))))
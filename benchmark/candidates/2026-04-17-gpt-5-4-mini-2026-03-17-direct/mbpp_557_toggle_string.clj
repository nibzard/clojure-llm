(defn toggle_string
  "	Write a function to toggle the case of all characters in a string."
  [string]
  (when string
    (apply str
           (map (fn [ch]
                  (let [c (int ch)]
                    (cond
                      (<= (int \a) c (int \z)) (char (- c 32))
                      (<= (int \A) c (int \Z)) (char (+ c 32))
                      :else ch)))
                string))))
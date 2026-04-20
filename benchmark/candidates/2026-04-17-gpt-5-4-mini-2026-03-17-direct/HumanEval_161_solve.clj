(defn solve
  [s]
  (let [s (or s "")
        letters? (some #(Character/isLetter ^char %) s)]
    (if letters?
      (apply str
             (map (fn [ch]
                    (if (Character/isLetter ^char ch)
                      (if (Character/isUpperCase ^char ch)
                        (Character/toLowerCase ^char ch)
                        (Character/toUpperCase ^char ch))
                      ch))
                  s))
      (apply str (reverse s)))))
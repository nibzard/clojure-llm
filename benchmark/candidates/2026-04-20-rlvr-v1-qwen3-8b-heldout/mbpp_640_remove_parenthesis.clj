(defn remove_parenthesis
  "	Write a function to remove the parenthesis and what is inbetween them from a string."
  [items]
  (if (nil? items)
    nil
    (let [s (str items)]
      (loop [ch (seq s)
             sb (java.lang.StringBuffer.)]
        (if-let [c (first ch)]
          (if (and (char?) c (= c \() )
            (recur (next ch) sb)
            (if (and (char?) c (= c \) ) )
              (recur (next ch) sb)
              (recur (next ch) (.append sb c))))
          (.toString sb))))))
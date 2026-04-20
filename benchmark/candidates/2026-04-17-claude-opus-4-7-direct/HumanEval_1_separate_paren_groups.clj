(defn separate_paren_groups
  "Input to this function is a string containing multiple groups of nested parentheses. 
  Your goal is to separate those group into separate strings and return the list of those.
  Separate groups are balanced (each open brace is properly closed) and not nested within each other.
  Ignore any spaces in the input string."
  [paren_string]
  (let [cleaned (clojure.string/replace paren_string #" " "")]
    (loop [chars (seq cleaned)
           depth 0
           current-group []
           groups []]
      (if (empty? chars)
        (if (empty? current-group)
          groups
          (conj groups (apply str current-group)))
        (let [c (first chars)]
          (cond
            (= c \()
            (recur (rest chars)
                   (inc depth)
                   (conj current-group c)
                   groups)
            
            (= c \))
            (let [new-depth (dec depth)
                  new-group (conj current-group c)]
              (if (zero? new-depth)
                (recur (rest chars)
                       new-depth
                       []
                       (conj groups (apply str new-group)))
                (recur (rest chars)
                       new-depth
                       new-group
                       groups)))
            
            :else
            (recur (rest chars) depth current-group groups)))))))
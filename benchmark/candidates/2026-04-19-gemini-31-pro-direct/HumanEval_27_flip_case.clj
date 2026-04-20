(defn flip_case
  "For a given string, flip lowercase characters to uppercase and uppercase to lowercase."
  [string]
  (apply str
         (map (fn [c]
                (cond
                  (Character/isUpperCase c) (Character/toLowerCase c)
                  (Character/isLowerCase c) (Character/toUpperCase c)
                  :else c))
              string)))
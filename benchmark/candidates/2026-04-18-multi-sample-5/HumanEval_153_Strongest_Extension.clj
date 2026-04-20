(defn Strongest_Extension
  "You will be given the name of a class (a string) and a list of extensions.
  The extensions are to be used to load additional classes to the class. The
  strength of the extension is as follows: Let CAP be the number of the uppercase
  letters in the extension's name, and let SM be the number of lowercase letters
  in the extension's name, the strength is given by the fraction CAP - SM.
  You should find the strongest extension and return a string in this
  format: ClassName.StrongestExtensionName.
  If there are two or more extensions with the same strength, you should
  choose the one that comes first in the list.
  For example, if you are given \"Slices\" as the class and a list of the
  extensions: ['SErviNGSliCes', 'Cheese', 'StuFfed'] then you should
  return 'Slices.SErviNGSliCes' since 'SErviNGSliCes' is the strongest extension
  (its strength is -1).
  Example:
  >>> (Strongest_Extension \"my_class\" [\"AA\" \"Be\" \"CC\"])
  \"my_class.AA\""
  [class_name extensions]
  (letfn [(strength [s]
            (reduce
             (fn [acc ch]
               (+ acc
                  (cond
                    (Character/isUpperCase ch) 1
                    (Character/isLowerCase ch) -1
                    :else 0)))
             0
             s))]
    (str class_name "."
         (reduce (fn [best ext]
                   (if (> (strength ext) (strength best)) ext best))
                 extensions))))
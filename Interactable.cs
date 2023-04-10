using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Interactable : MonoBehaviour
{
    [SerializeField] public DialogScript dialogScript;
    public bool isInteractable = false;
    public bool isEndable = false;
    private Collider collider = null;
    [SerializeField] public GameObject interactPrompt;
    [SerializeField] public PlayerMovementTutorial movement;
    [SerializeField] public FadeToBlack fadeToBlack;
    public void Start() 
    {
        interactPrompt.SetActive(false);
    }

    public void Update() {

        if (isInteractable && Input.GetKeyDown(KeyCode.E)) {
        
            Interact(GetDialog(collider));        
        }
        if (isInteractable && isEndable && Input.GetKeyDown(KeyCode.Y))
        {
            movement.enabled = false;
            fadeToBlack.StartCoroutine(fadeToBlack.FadeBlackOutSquare(true));
            fadeToBlack.StartCoroutine(fadeToBlack.SwitchSceneAfterTime(12.0f, 3));
        }
        if (isInteractable && isEndable && Input.GetKeyDown(KeyCode.N))
        {
            movement.enabled = false;
            fadeToBlack.StartCoroutine(fadeToBlack.FadeBlackOutSquare(true));
            fadeToBlack.StartCoroutine(fadeToBlack.SwitchSceneAfterTime(12.0f, 2));
        }
    }

    private DialogObject GetDialog(Collider collider) {
        
        DialogActivator dialogActivator = collider.gameObject.GetComponent<DialogActivator>();
        return dialogActivator.dialogObject;
    }

    private void OnTriggerEnter(Collider other) {

        collider = other;
        //checks if the collider that has been triggered is an interactable
        if (other.gameObject.layer == 9)
        {
            //set boolean for interaction function to true
            isInteractable = true;
            Debug.Log("trigger works");
            interactPrompt.SetActive(true);
            //if e is pressed get the component

        }
        if (other.gameObject.layer == 10) {
            isEndable = true;
        }
        
    }

    private void OnTriggerExit(Collider other) {

        dialogScript.CloseDialogBox();
        interactPrompt.SetActive(false);
        collider = null;
        //set boolean for interaction function to false
        if (other.gameObject.layer == 9) {
            isInteractable = false;
            Debug.Log("out");
        }
    }

    public void Interact(DialogObject dialogObject) 
    {
        dialogScript.ClearDialog();
        dialogScript.ShowDialog(dialogObject);
    }
}

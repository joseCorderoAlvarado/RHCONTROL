function deleteEventBitacora(xhr, status, args) {
    if (undefined === args.validationFailed || false === args.validationFailed) {
        PF('deleteEventBitacora').hide();
    } else {
        PF('deleteEventBitacora').show();
    }
}